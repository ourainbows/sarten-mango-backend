package com.sartenmang.sartenmango.servicios;

import java.util.List;
import java.util.Optional;

import com.sartenmang.sartenmango.entidades.Cookware;
import com.sartenmang.sartenmango.repositorios.CookwareRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CookwareService {

    @Autowired
    public CookwareRepository cRepository;

    public List<Cookware> getTools() {
        return cRepository.getTools();
    }

    public Optional<Cookware> getTool(String reference) {
        return cRepository.getTool(reference);
    }

    public Cookware createTool(Cookware tool) {
        if (tool.getReference() == null) {
            return tool;
        } else {
            return cRepository.createTool(tool);
        }
    }

    public Cookware updateTool(Cookware tool) {
        if (tool.getReference() != null) {
            Optional<Cookware> opTool = cRepository.getTool(tool.getReference());
            if (!opTool.isEmpty()) {
                if (tool.getBrand() != null) {
                    opTool.get().setBrand(tool.getBrand());
                }
                if (tool.getCategory() != null) {
                    opTool.get().setCategory(tool.getCategory());
                }
                if (tool.getMateriales() != null) {
                    opTool.get().setMateriales(tool.getMateriales());
                }
                if (tool.getDimensiones() != null) {
                    opTool.get().setDimensiones(tool.getDimensiones());
                }
                if (tool.getDescription() != null) {
                    opTool.get().setDescription(tool.getDescription());
                }
                if (tool.getPrice() != 0.0) {
                    opTool.get().setPrice(tool.getPrice());
                }
                if (tool.getQuantity() >= 0) {
                    opTool.get().setQuantity(tool.getQuantity());
                }
                if (tool.getPhotography() != null) {
                    opTool.get().setPhotography(tool.getPhotography());
                }
                opTool.get().setAvailability(tool.isAvailability());
                cRepository.updateTool(opTool.get());
                return opTool.get();
            } else {
                return tool;
            }
        } else {
            return tool;
        }
    }

    public void deleteTool(String reference) {
        if (getTool(reference) != null) {
            cRepository.deleteTool(reference);
        }
    }

    public List<Cookware> gadgetsByPrice(double price) {
        return cRepository.gadgetsByPrice(price);
    }

    public List<Cookware> findByDescriptionLike(String description) {
        return cRepository.findByDescriptionLike(description);
    }
}
