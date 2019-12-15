package com.geekbrains.traning.tasks.repositories.specifications;


import com.geekbrains.traning.tasks.entities.*;
import org.springframework.data.jpa.domain.Specification;

public class TaskSpecifications {
    public static Specification<Task> titleContains(String word) {
        return (Specification<Task>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%" + word + "%");
    }

    public static Specification<Task> ownerEquals(User user) {
        return (Specification<Task>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("owner"), user);
    }

    public static Specification<Task> executerEquals(User user) {
        return (Specification<Task>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("executer"), user);
    }

    public static Specification<Task> statusEquals(Status status) {
        return (Specification<Task>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), status);
    }
}
