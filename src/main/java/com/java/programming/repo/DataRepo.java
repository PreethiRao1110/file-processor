package com.java.programming.repo;

import com.java.programming.entity.DataEntity;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepo extends CassandraRepository<DataEntity, Long> {
}
