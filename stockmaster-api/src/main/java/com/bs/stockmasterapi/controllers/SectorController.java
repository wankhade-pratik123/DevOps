package com.bs.stockmasterapi.controllers;

import static org.springframework.http.ResponseEntity.status;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bs.stockmasterapi.model.Sector;
import com.bs.stockmasterapi.services.SectorService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/sector")
@AllArgsConstructor
public class SectorController {

	private final SectorService sectorService; 
	
	@PostMapping
    public ResponseEntity<Void> createSector(@RequestBody Sector sector) {
		sectorService.createSector(sector);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Sector>> getAllSector() {
        return status(HttpStatus.OK).body(sectorService.getAllSector());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sector> getSector(@PathVariable Long id) {
        return status(HttpStatus.OK).body(sectorService.getSector(id));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSector(@PathVariable Long id) {
        return status(HttpStatus.OK).body(sectorService.deleteSector(id));
    }
}
