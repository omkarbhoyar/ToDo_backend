package com.InfoOrigin.ToDoApp.Repository;

import com.InfoOrigin.ToDoApp.DTO.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepos extends JpaRepository<Tasks, Integer> {
    List<Tasks> findByCompleted(boolean completed);
}
