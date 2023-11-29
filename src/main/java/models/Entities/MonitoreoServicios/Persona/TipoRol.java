package models.Entities.MonitoreoServicios.Persona;

import io.javalin.security.RouteRole;

public enum TipoRol implements RouteRole {
    ADMIN,
    USER,
    MEMBER,
    COMMUNITY_ADMIN
}
