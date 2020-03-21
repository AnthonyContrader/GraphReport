package it.contrader.converter;

import org.springframework.stereotype.Component;

import it.contrader.dto.UserDTO;

import it.contrader.model.User;

@Component
public class UserConverter extends AbstractConverter<User, UserDTO> {

	@Override
	public User toEntity(UserDTO userDTO) {
		User user = null;
		if (userDTO != null) {
			user = new User(userDTO.getId(), userDTO.getUsername(), userDTO.getPassword(), userDTO.getUsertype(),
					userDTO.getNome(),userDTO.getCognome(),userDTO.getEmail(),userDTO.getCitta(),userDTO.getNazione(),null);
		}
		return user;
	}

	@Override
	public UserDTO toDTO(User user) {
		UserDTO userDTO = null;
		if (user != null) {
			userDTO = new UserDTO(user.getId(), user.getUsername(), user.getPassword(), user.getUsertype(), 
					user.getNome(), user.getCognome(), user.getEmail(), user.getCitta(), user.getNazione());

		}
		return userDTO;
	}
}