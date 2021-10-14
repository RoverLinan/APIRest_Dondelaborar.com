package com.springweb.dondelaborar.models;

import javax.persistence.*;

@Entity
public class Curriculum {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = true)
    private int id;


    

}
