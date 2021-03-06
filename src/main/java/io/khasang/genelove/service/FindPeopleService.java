package io.khasang.genelove.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/*
    Stub class for findPeople & searchResults jsp
 */
@Service
@Transactional
public class FindPeopleService {

    public List<ProfileServiceStub> findPeople(String firstName, String lastName, String region, String minAge, String maxAge) {
        List<ProfileServiceStub> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(new ProfileServiceStub(firstName + i,lastName + i,"02/02/1991", String.valueOf(i)));
        }
        return list;
    }
}
