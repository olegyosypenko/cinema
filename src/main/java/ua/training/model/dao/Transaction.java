package ua.training.model.dao;

public interface Transaction extends AutoCloseable {

    /**
     * Commits all changes since the last commit.
     */
    void commit();

    /**
     * This method starts transaction so any query after calling this method will be not committed, until
     * explicit call of method commit.
     */
    void startTransaction();


    /**
     * This method rollbacks all the changes that were done since last commit.
     */
    void rollback();

    /**
     * This method prevents such problems as Dirty reads, Non-repeatable reads, Phantom reads
     */
    void setSerializable();

    void close();
}
