package co.ucentral.AppventaVehiculos.servicios;


import co.ucentral.AppventaVehiculos.persistencia.entidades.Usuario;
import co.ucentral.AppventaVehiculos.persistencia.repositorio.UsuarioRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@AllArgsConstructor
@Service
public class UsuarioServicio {

    @Autowired
    UsuarioRepositorio usuarioRepositorio;
    public void registrarUsuario(Usuario usuario) {
        usuarioRepositorio.save(usuario);
    }

    public List<Usuario> obtenerTodos(){
        List<Usuario> listado = (List<Usuario>) usuarioRepositorio.findAll();
        return listado;
    }

    public boolean borrar(Usuario usuario){
        try{
            usuarioRepositorio.delete(usuario);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean validarCredenciales(String usuario, String contrasena) {
        co.ucentral.AppventaVehiculos.persistencia.entidades.Usuario user = usuarioRepositorio.findByUsuarioAndContrasena(usuario, contrasena);
        return user != null;
    }
}