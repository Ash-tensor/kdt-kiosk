package ac.su.kiosk.service;

import ac.su.kiosk.domain.TestEntity;
import ac.su.kiosk.repository.TestRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestRepo testRepo;

    public String test(Long id) {
        Optional<TestEntity> temp = testRepo.findById(id);
        TestEntity temp2 = temp.get();
        return temp2.testString;
    }
}
