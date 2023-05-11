package sangmyungdae.deliciousclimbing.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sangmyungdae.deliciousclimbing.domain.entity.TbAddress;
import sangmyungdae.deliciousclimbing.domain.entity.TbFamousMountain;
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;
import sangmyungdae.deliciousclimbing.dto.*;
import sangmyungdae.deliciousclimbing.repository.AddressRepository;
import sangmyungdae.deliciousclimbing.repository.FamousMountainRepository;
import sangmyungdae.deliciousclimbing.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final FamousMountainRepository famousMountainRepository;
    private final AddressRepository addressRepository;

    @Transactional
    public String signIn (UserSign dto) {
        TbUser entity = userRepository.findByEmail(dto.getEmail()).orElseThrow();

        if(entity.getPassword() != dto.getPassword()) {
            // throw exception;
        }
        // return apiKey;
        return null;
    }

    @Transactional
    public User getUser(Long id) {
        TbUser entity = userRepository.findById(id).orElseThrow();

        return User.builder()
                .entity(entity)
                .build();
    }

    @Transactional
    public User createUser(UserRegister dto) {

        // password Encoder 처리

        // Request DTO to Entity
        TbUser entity = userRepository.save(dto.toEntity());

        // Entity to Response DTO
        return User.builder()
                .entity(entity)
                .build();
    }

    @Transactional
    public User updateUser(Long id, UserDto dto) {
        TbUser entity = userRepository.findById(id).orElseThrow();
        TbFamousMountain famousMountain = famousMountainRepository.findById(dto.getFamousMountainId()).orElseThrow();
        TbAddress address = addressRepository.findByCode(dto.getAddressCode());
        entity.updateInfo(dto.getNickname(), dto.getImageUrl(), dto.getIntroduction(), dto.getDifficulty(),
                dto.getSns(), famousMountain, address);

        return User.builder().entity(entity).build();
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public void changePassword(UserPassword dto) {

    }

}