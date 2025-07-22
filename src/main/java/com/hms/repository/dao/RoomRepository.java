package com.hms.repository.dao;

import com.hms.repository.dmo.Room;
import com.hms.repository.dmo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
