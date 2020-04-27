package com.example.letts_shop.repositories;

import com.example.letts_shop.models.DBFile;
import com.example.letts_shop.models.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DBFileRepository extends CrudRepository<DBFile, Integer> {
    public DBFile getFileByProductId(int id);

    public List<DBFile> getFilesByProductId(int id);
}
