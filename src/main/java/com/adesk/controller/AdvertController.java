package com.adesk.controller;

import com.adesk.DTO.response.AdvertDTO;
import com.adesk.DTO.response.PhotoDTO;
import com.adesk.dao.SubCategoryDao;
import com.adesk.models.Advert;
import com.adesk.models.SubCategory;
import com.adesk.models.User;
import com.adesk.service.AdvertService;
import com.adesk.service.ImageLoadService;
import com.adesk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/advert")
public class AdvertController {
    @Autowired
    private AdvertService advertService;

    @Autowired
    private UserService userService;

    @Autowired
    private SubCategoryDao subCategoryDao;
    @Autowired
    private ImageLoadService imageLoadService;

    private String uploadedPhoto;

    @PostMapping("/add")
    void AddAdvert(@RequestBody AdvertDTO newAdvert, Principal principal) {

        User user = (User) userService.loadUserByUsername(principal.getName());
        Advert advert = new Advert(newAdvert.getTitle(), new Date(), newAdvert.getPrice(), newAdvert.getDescription(),
                user, subCategoryDao.findById(newAdvert.getCategoryId()), newAdvert.getPhoto());

        advertService.save(advert);
    }

    @GetMapping("/getAll")
    List<AdvertDTO> getAll() {
        return  advertService.findAll();

    }
    @GetMapping("/getAll/{username}")
    List<AdvertDTO> getAll(@PathVariable(value = "username") String username) {

        User user = (User) userService.loadUserByUsername(username);
        return  advertService.findByUser(user);

    }

    private String getPhotoPath() {
        return System.getProperty("user.home") + File.separator + "Documents" + File.separator
                + "IdeaProjects" + File.separator + "Course Project" + File.separator + "Project" +
                File.separator + "adesk" + File.separator + "client" + File.separator + "src" +
                File.separator + "assets" + File.separator + "advetrs" + File.separator;
    }

    @PostMapping("/upload-photo")
    public PhotoDTO uploadFile(@RequestParam("file") MultipartFile upFile) {

        uploadedPhoto = upFile.getOriginalFilename();
        try {
            imageLoadService.load(upFile, getPhotoPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        PhotoDTO photoDTO = new PhotoDTO();
        photoDTO.setPhoto(uploadedPhoto);

        return photoDTO;
    }

    @GetMapping("/delete-upload-photo")
    public void deleteUploadedPhoto()
    {
        if (!uploadedPhoto.equals("noavatar.jpg")) {
            try {
                Files.delete(FileSystems.getDefault().getPath(getPhotoPath(), uploadedPhoto));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @GetMapping("delete/{id}")
    void deleteAdvert(@PathVariable(value = "id") int id) {
         advertService.delete(id);
        System.out.println("deleted");
    }

    @GetMapping("/{id}")
    AdvertDTO getById(@PathVariable(value = "id") int id)
    {
        return advertService.findById(id);
    }
}
