package com.fabiangomez.service.impl;

import com.fabiangomez.persistence.entity.PizzaEntity;
import com.fabiangomez.service.PizzaService;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author FABIANG
 */
@Service
public class PizzaServiceJdbcTemplate implements PizzaService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PizzaServiceJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PizzaEntity> getAll() {
        String sql = "SELECT * FROM PIZZA";
        return this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(PizzaEntity.class));

    }

    @Override
    public Optional<PizzaEntity> getById(Integer id) {
        String sql = "SELECT * FROM PIZZA WHERE id_pizza = ?";

        try {
            PizzaEntity pizza = this.jdbcTemplate.queryForObject(
                    sql,
                    new BeanPropertyRowMapper<>(PizzaEntity.class),
                    id
            );
            return Optional.ofNullable(pizza);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public PizzaEntity save(PizzaEntity pizza) {
        String sql = "INSERT INTO PIZZA (name, description, price, vegetarian, vegan, available) VALUES(?,?,?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, pizza.getName());
                    ps.setString(2, pizza.getDescription());
                    ps.setDouble(3, pizza.getPrice());
                    ps.setBoolean(4, pizza.getVegetarian());
                    ps.setBoolean(5, pizza.getVegan());
                    ps.setBoolean(6, pizza.getAvailable());
                    return ps;
                },
                keyHolder
        );

        Number generatedId = keyHolder.getKey();
        if (generatedId != null) {
            pizza.setIdPizza(generatedId.intValue());
        }

        return pizza;

    }

    @Override
    public Optional<PizzaEntity> update(PizzaEntity pizza, Integer id) {
        String sql = "UPDATE PIZZA SET name = ?, description = ?, price = ?, vegetarian = ?, vegan = ?, available = ? WHERE id_pizza = ?";

        int rowsAffect = jdbcTemplate.update(
                sql,
                pizza.getName(),
                pizza.getDescription(),
                pizza.getPrice(),
                pizza.getVegetarian(),
                pizza.getVegan(),
                pizza.getAvailable(),
                id
        );

        if (rowsAffect > 0) {
            pizza.setIdPizza(id);
            return Optional.of(pizza);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM PIZZA WHERE id_pizza = ?";

        int rowsAffect = jdbcTemplate.update(sql, id);

        return rowsAffect > 0;
    }

}
