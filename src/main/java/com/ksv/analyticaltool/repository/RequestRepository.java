package com.ksv.analyticaltool.repository;

import com.ksv.analyticaltool.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Long> {
}
