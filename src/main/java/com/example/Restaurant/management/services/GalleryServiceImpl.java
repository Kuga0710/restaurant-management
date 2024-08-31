package com.example.Restaurant.management.services;

import com.example.Restaurant.management.entities.Gallery;
import com.example.Restaurant.management.repositories.GalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class GalleryServiceImpl implements GalleryService {

    @Autowired
    private GalleryRepository galleryRepository;

    @Override
    public Gallery createGallery(MultipartFile file, String type) {
        Gallery gallery = new Gallery();
        gallery.setType(type);

        if (file != null && !file.isEmpty()) {
            try {
                byte[] imageBytes = file.getBytes();
                gallery.setImage(imageBytes);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Error processing file", e);
            }
        } else {
            throw new RuntimeException("File is null or empty");
        }

        return galleryRepository.save(gallery);
    }

    @Override
    public List<Gallery> getGalleryByType(String type) {
        return type != null && !type.isEmpty() ? galleryRepository.findByType(type) : galleryRepository.findAll();
    }

    @Override
    public Gallery updateGallery(Long id, MultipartFile file, String type) {
        Gallery gallery = galleryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invalid Gallery ID"));

        gallery.setType(type);

        if (file != null && !file.isEmpty()) {
            try {
                byte[] imageBytes = file.getBytes();
                gallery.setImage(imageBytes);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Error processing file", e);
            }
        }

        return galleryRepository.save(gallery);
    }

    @Override
    public boolean deleteGallery(Long id) {
        if (galleryRepository.existsById(id)) {
            galleryRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}

