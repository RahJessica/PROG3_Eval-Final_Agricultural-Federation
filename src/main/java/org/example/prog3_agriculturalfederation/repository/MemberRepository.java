package org.example.prog3_agriculturalfederation.repository;

import org.example.prog3_agriculturalfederation.entity.Member;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemberRepository {

        private final Map<String, Member> storage = new HashMap<>();

        public Member save(Member member) {
            storage.put(member.getId(), member);
            return member;
        }

        public Optional<Member> findById(String id) {
            return Optional.ofNullable(storage.get(id));
        }

        public List<Member> findAll() {
            return new ArrayList<>(storage.values());
        }
    }

