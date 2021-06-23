package com.demo.ldap.dao;

import org.springframework.data.repository.CrudRepository;

import javax.naming.Name;

public interface PersonRepository extends CrudRepository<Person, Name> {
}
