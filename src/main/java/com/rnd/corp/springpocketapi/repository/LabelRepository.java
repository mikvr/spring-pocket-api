package com.rnd.corp.springpocketapi.repository;

import com.rnd.corp.springpocketapi.domain.Label;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepository  extends JpaRepository<Label, Long> {

    Boolean existsById(int id);
}
