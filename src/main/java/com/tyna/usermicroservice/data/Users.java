package com.tyna.usermicroservice.data;

import java.time.LocalDate;
import java.time.Period;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Users {
    private String name;
    private String email;

    @JsonProperty("birthDate")
    private LocalDate birthDate;
    public Users() {} // ⚠️ Nécessaire pour que Spring Boot puisse créer l'objet via JSON

    public Users(String name, String email, LocalDate birthDate) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
    }

    public String getName(){ return name; }
    public String getEmail(){ return email; }
    public LocalDate getBirthDate(){ return birthDate; }

    public void setName(String name){ this.name = name; }
    public void setEmail(String email){ this.email = email; }
    public void setBirthDate(LocalDate birthDate){ this.birthDate = birthDate; }

    // 🟢 Calcul automatique de l'âge basé sur la date de naissance
    public int getAge() {
        return Period.between(this.birthDate, LocalDate.now()).getYears();
    }
}
