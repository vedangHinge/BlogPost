package com.cdac.service;

import java.util.UUID;

import com.cdac.entity.User;

public interface UserService {
User getById(UUID userId);
}
