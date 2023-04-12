package com.utm.msei.persistence.service;

import com.utm.msei.persistence.dto.AdministratieDto;
import com.utm.msei.persistence.dto.ProfesorDto;
import com.utm.msei.persistence.dto.UserDto;
import com.utm.msei.persistence.dto.enums.EntityTypeEnum;
import com.utm.msei.persistence.mapper.AdministratieMapper;
import com.utm.msei.persistence.mapper.ParintiMapper;
import com.utm.msei.persistence.mapper.ProfesorMapper;
import com.utm.msei.persistence.mapper.UserMapper;
import com.utm.msei.persistence.repository.AdministratieRepository;
import com.utm.msei.persistence.repository.ProfesorRepository;
import com.utm.msei.persistence.repository.UserRepository;
import com.utm.msei.security.PasswordHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.utm.msei.Main.serviceHandler;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;
    private final AdministratieRepository administratieRepository;
    private final AdministratieMapper administratieMapper;

    private final ProfesorRepository profesorRepository;
    private final ProfesorMapper profesorMapper;


    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper,
                       AdministratieRepository administratieRepository, AdministratieMapper administratieMapper,
                       ProfesorRepository profesorRepository, ProfesorMapper profesorMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.administratieRepository = administratieRepository;
        this.administratieMapper = administratieMapper;
        this.profesorRepository = profesorRepository;
        this.profesorMapper = profesorMapper;
    }

    @Transactional
    public UserDto save(UserDto userDto) {
        return userMapper.toDto(userRepository.save(userMapper.toEntity(userDto)));
    }

    @Transactional
    public void delete(int idUser) {
        if (userRepository.existsById(idUser)) {
            userRepository.deleteById(idUser);
        } else {
            throw new IllegalStateException("no user");
        }
    }

    @Transactional
    public void updatePozaBy(UserDto userDto) {
        if (userRepository.existsById(userDto.getId())) {
            int i = userRepository.updatePozaBy(userDto.getPoza());
        }
    }

    @Transactional
    public UserDto findByCredentials(String email, String password) {
        UserDto userDto = userMapper.toDto(userRepository.findByEmail(email));
        if (userDto != null) {
            String[] salt_hash = PasswordHandler.getSaltAndHashFromRecord(userDto.getPassword());
            if (PasswordHandler.validatePassword(password, salt_hash[0], salt_hash[1])) {
                return userDto;
            }
        }
        return null;
    }

    public List<UserDto> getAll() {
        return userMapper.toDto(userRepository.findAll());
    }

    public int update(UserDto userDto) {
        /**
         * if in past was other entity (was admin and now is prof), then delete and create instance correspondingly
         */
        if ((userDto.getUserType().contains(EntityTypeEnum.DIRECTOR) || userDto.getUserType().contains(EntityTypeEnum.ADJUNCT)) && (userDto.getUserType().contains(EntityTypeEnum.PROFESOR))) {
            ProfesorDto profDto = serviceHandler.getProfesorService().findByUserId(userDto.getId());
            AdministratieDto adminDto = serviceHandler.getAdministratieService().findByUserId(userDto.getId());
            if (profDto == null) {
                profDto = new ProfesorDto();
                profDto.setIdUser(userDto);
                profesorRepository.save(profesorMapper.toEntity(profDto));
            }
            if (adminDto == null) {
                adminDto = new AdministratieDto();
                adminDto.setIdUser(userDto);
                administratieRepository.save(administratieMapper.toEntity(adminDto));
            }
        } else {
            if (userDto.getUserType().contains(EntityTypeEnum.DIRECTOR) || userDto.getUserType().contains(EntityTypeEnum.ADJUNCT)) {
                ProfesorDto profDto = serviceHandler.getProfesorService().findByUserId(userDto.getId());
                if (profDto != null) {
                    profesorRepository.deleteById(profDto.getId());
                    AdministratieDto administratieDto = new AdministratieDto();
                    administratieDto.setIdUser(userDto);
                    administratieRepository.save(administratieMapper.toEntity(administratieDto));
                }
            } else if (userDto.getUserType().contains(EntityTypeEnum.PROFESOR)) {
                AdministratieDto adminDto = serviceHandler.getAdministratieService().findByUserId(userDto.getId());
                if (adminDto != null) {
                    administratieRepository.deleteById(adminDto.getId());
                    ProfesorDto profDto = new ProfesorDto();
                    profDto.setIdUser(userDto);
                    profesorRepository.save(profesorMapper.toEntity(profDto));
                }
            }
        }
        return userRepository.updateNumeAndPrenumeAndUserTypeAndIdnpAndTelefonAndDataNastereBy(userDto.getNume(), userDto.getPrenume(), userDto.getUserType().stream().map(Enum::name)
                .collect(Collectors.joining(",")), userDto.getIdnp(), userDto.getTelefon(), userDto.getDataNastere(), userDto.getId());
    }

    public void updatePassword(int id, String pass) {
        userRepository.updatePasswordById(pass, id);
    }
}
