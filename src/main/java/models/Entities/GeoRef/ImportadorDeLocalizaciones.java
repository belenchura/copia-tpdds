package models.Entities.GeoRef;
import models.Entities.GeoRef.entities.*;
import models.Entities.MonitoreoServicios.Localizacion.Departamento;
import models.Entities.MonitoreoServicios.Localizacion.Localidad;
import models.Entities.MonitoreoServicios.Localizacion.Municipio;
import models.Entities.MonitoreoServicios.Localizacion.Provincia;
import models.Entities.GeoRef.entities.*;
import models.Entities.MonitoreoServicios.Localizacion.*;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.stream.Collectors;

public class ImportadorDeLocalizaciones {

    public static List<Provincia> importarLocalizaciones() throws IOException{
        ListadoDeProvincias listadoDeProvinciasGeoref= listaProvinciasGeoref();

        /// HASTA ACA, SOLO TRAJIMOS LAS CLASES DE GEOREF

        List<Provincia> listadoDeProvincias = listadoDeProvinciasGeoref.provincias.stream().map(p->instanciarProvincia(p)).collect(Collectors.toList());
        for(int i=0;i<listadoDeProvincias.size();i++){
            List<Departamento> departamentos=new ArrayList<>();
            for(int j=0;j<listadoDeProvinciasGeoref.provincias.get(i).departamentos.departamentos.size();j++){
            departamentos.add(instanciarDepartamento(listadoDeProvinciasGeoref.provincias.get(i).departamentos.departamentos.get(j)));
            }
            listadoDeProvincias.get(i).setDepartamentos(departamentos);
        }
        for(int i=0;i<listadoDeProvincias.size();i++){
            List<Municipio> municipios=new ArrayList<>();
            for(int j=0;j<listadoDeProvinciasGeoref.provincias.get(i).municipios.municipios.size();j++){
                municipios.add(instanciarMunicipio(listadoDeProvinciasGeoref.provincias.get(i).municipios.municipios.get(j),listadoDeProvincias.get(i)));

            }
            for (Municipio municipio: municipios) {

                for(int k=0;k<listadoDeProvinciasGeoref.provincias.get(i).municipios.municipios.get(k).localidades.localidades.size();k++) {
                    List<Localidad>localidades=new ArrayList<>();
                    for (int l=0;l<listadoDeProvinciasGeoref.provincias.get(i).municipios.municipios.get(k).localidades.localidades.size();l++){
                            localidades.add(instanciarLocalidad(listadoDeProvinciasGeoref.provincias.get(i).municipios.municipios.get(k).localidades.localidades.get(l),
                                    listadoDeProvincias.get(i),
                                    listadoDeProvincias.get(i).getMunicipios().get(k)));
                    }
                }
            }

            listadoDeProvincias.get(i).setMunicipios(municipios);
        }

        return listadoDeProvincias;
    }

    private static ListadoDeProvincias listaProvinciasGeoref() throws IOException{
        ServicioGeoref servicioGeoref = ServicioGeoref.instancia();

        ListadoDeProvincias listadoDeProvinciasGeoref = servicioGeoref.listadoDeProvincias();
        listadoDeProvinciasGeoref.provincias.forEach(p-> {
            try {
                p.departamentos= servicioGeoref.listadoDeDepartamentos(p);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        listadoDeProvinciasGeoref.provincias.forEach(p-> {
            try {
                p.municipios= servicioGeoref.listadoDeMunicipiosDeProvincia(p);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        listadoDeProvinciasGeoref.provincias.stream().flatMap(p->p.municipios.municipios.stream()).forEach(m-> {
            try {
                m.localidades= servicioGeoref.listadoDeLocalidadesDeMunicipio(m);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return listadoDeProvinciasGeoref;
    }

    private static Provincia instanciarProvincia(ProvinciaGeoRef provinciaGeoRef){
        Provincia provincia = new Provincia(provinciaGeoRef.nombre, (long) provinciaGeoRef.id);
        return provincia;
    }

    private static Departamento instanciarDepartamento(DepartamentoGeoref departamentosGeoref){
        Departamento departamento = new Departamento(departamentosGeoref.nombre, (long)departamentosGeoref.id);
        return departamento;
    }

    private static Municipio instanciarMunicipio(MunicipioGeoRef municipioGeoRef, Provincia provincia){
        Municipio municipio = new Municipio(municipioGeoRef.nombre, (long) municipioGeoRef.id, provincia);
        return municipio;
    }
    private static Localidad instanciarLocalidad(LocalidadGeoref localidadGeoref, Provincia provincia, Municipio municipio){
        Localidad localidad = new Localidad(localidadGeoref.nombre, (long) localidadGeoref.id, provincia, municipio);
        return localidad;
    }

}