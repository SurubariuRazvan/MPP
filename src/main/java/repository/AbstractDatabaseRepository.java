package repository;

import domain.Entity;
import validation.CRUDValidator;
import validation.ValidationException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDatabaseRepository<ID, E extends Entity<ID>> implements CRUDRepository<ID, E> {
    private final Connection c;
    private final CRUDValidator<E> validator;

    AbstractDatabaseRepository(CRUDValidator<E> validator, Connection c) {
        this.validator = validator;
        this.c = c;
    }

    /**
     * @param result Sql result
     * @return an entity that can be understood by the program
     * @throws SQLException in case that the conversion fails
     */
    protected abstract E readEntity(ResultSet result) throws SQLException;

    @Override
    public E findOne(ID id) {
        if (id == null)
            throw new IllegalArgumentException("id is null");
        E entity = null;
        try {
            Statement stmt = c.createStatement();
            var f = stmt.executeQuery(findOneString(id));
            if (f.next())
                entity = readEntity(f);
            f.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    /**
     * @param id the id of the searched entity
     * @return a query that finds the entity with the specified id
     */
    protected abstract String findOneString(ID id);

    @Override
    public Iterable<E> findAll() {
        List<E> entities = new ArrayList<>();
        try {
            Statement stmt = c.createStatement();
            var f = stmt.executeQuery(findAllString());
            while(f.next())
                entities.add(readEntity(f));
            f.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entities;
    }

    /**
     * @return a query that finds all the entities
     */
    protected abstract String findAllString();

    @Override
    public E save(E entity) throws ValidationException {
        if (entity == null)
            throw new IllegalArgumentException("entity is null");
        validator.validate(entity);
        try {
            Statement stmt = c.createStatement();
            stmt.executeUpdate(insertString(entity));
            stmt.close();
        } catch (SQLException e) {
            if (e.getMessage().contains("duplicate"))
                return entity;
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param entity the entity to be inserted
     * @return a query that inserts the entity
     */
    protected abstract String insertString(E entity);

    @Override
    public E delete(ID id) {
        if (id == null)
            throw new IllegalArgumentException("id is null");
        try {
            Statement stmt = c.createStatement();
            E entity = this.findOne(id);
            if (entity != null) {
                stmt.executeUpdate(deleteString(id));
                stmt.close();
                return entity;
            }
            stmt.close();
        } catch (SQLException e) {
            if (e.getMessage().contains("violates foreign key constraint"))
                throw new RepositoryException("Appears in another table.");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param id the id of the entity that will be deleted
     * @return a query that deletes the entity with the specified id
     */
    protected abstract String deleteString(ID id);

    @Override
    public E update(E entity) {
        if (entity == null)
            throw new IllegalArgumentException("entity is null");
        validator.validate(entity);
        try {
            Statement stmt = c.createStatement();
            int action = stmt.executeUpdate(updateString(entity));
            stmt.close();
            if (action == 0)
                return entity;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param entity the entity that will update the one that is found with the same id in the database
     * @return a query that updates the entity with the new one
     */
    protected abstract String updateString(E entity);
}
