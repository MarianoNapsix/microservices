package com.usuario.service.service;

import com.usuario.service.entity.Usuario;
import com.usuario.service.feignclient.CarFeignClient;
import com.usuario.service.feignclient.MotoFeignClient;
import com.usuario.service.models.Car;
import com.usuario.service.models.Moto;
import com.usuario.service.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UsuarioService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CarFeignClient carFeignClient;

    @Autowired
    private MotoFeignClient motoFeignClient;

    @Autowired
    UsuarioRepository usuarioRepository;

    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(int id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Car> getCars(int usuarioId) {
        List<Car> cars = restTemplate.getForObject("http://localhost:8002/car/usuario/" + usuarioId, List.class);
        return cars;
    }
    public List<Moto> getMotos(int usuarioId) {
        List<Moto> motos = restTemplate.getForObject("http://localhost:8003/moto/usuario/" + usuarioId, List.class);
        return motos;
    }

    public Car saveCar(int usuarioId, Car car) {
        car.setUsuarioId(usuarioId);
        return carFeignClient.save(car);
    }
    public Moto saveMoto(int usuarioId, Moto moto) {
        moto.setUsuarioId(usuarioId);
        return motoFeignClient.save(moto);
    }

    public List<Moto> getMotosByFeign(int usuarioId) {
        List<Moto> motos = motoFeignClient.getMotos(usuarioId);
        return motos;
    }

    public Map<String, Object> getUsuariosYVehiculos(int usuarioId) {
        Map<String, Object> resultado = new HashMap<>();

        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        if (Objects.isNull(usuario)) {
            resultado.put("Usuario", "El usuario no existe");
            return resultado;
        }
        resultado.put("Usuario", usuario);

        List<Car> cars = carFeignClient.getCars(usuarioId);
        if (Objects.isNull(cars) || cars.isEmpty()) {
            resultado.put("Cars", "El usuario no tiene cars");
        } else {
            resultado.put("Cars", cars);
        }

        List<Moto> motos = motoFeignClient.getMotos(usuarioId);
        if (Objects.isNull(motos) || motos.isEmpty()) {
            resultado.put("Motos", "El usuario no tiene motos");
        } else {
            resultado.put("Motos", motos);
        }

        return resultado;
    }
}
