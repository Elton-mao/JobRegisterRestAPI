package com.estudoapi.estudoapi.api.Skills.assemblers;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.estudoapi.estudoapi.api.Skills.controllers.SkillRestController;
import com.estudoapi.estudoapi.api.Skills.dtos.SkillReSponse;
@Component
public class SkillAssembler implements SimpleRepresentationModelAssembler<SkillReSponse> {

    @Override
    public void addLinks(EntityModel<SkillReSponse> resource) {
                  var id = resource.getContent().getId();
                    var selfLink = WebMvcLinkBuilder.linkTo(
                            WebMvcLinkBuilder.methodOn(SkillRestController.class).findById(id))
                            .withSelfRel().withType("GET");
                    resource.add(selfLink);
                    var updateLink = WebMvcLinkBuilder.linkTo(
                            WebMvcLinkBuilder.methodOn(SkillRestController.class).update(id, null))
                            .withRel("update").withType("PUT");
                    var deleteLink = WebMvcLinkBuilder.linkTo(
                            WebMvcLinkBuilder.methodOn(SkillRestController.class).delete(id))
                            .withRel("delete").withType("DELETE");
                    resource.add(selfLink, updateLink, deleteLink);
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<SkillReSponse>> resources) {
        var selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SkillRestController.class).findAll(null))
        .withSelfRel()
        .withType("GET");

         var createLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SkillRestController.class).create(null))
        .withRel("create")
        .withType("POST");

        resources.add(selfLink,createLink);

   
    }
    
}
