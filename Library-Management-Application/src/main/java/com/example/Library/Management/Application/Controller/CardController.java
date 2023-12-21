package com.example.Library.Management.Application.Controller;

import com.example.Library.Management.Application.Entities.LibraryCard;
import com.example.Library.Management.Application.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")

public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping("/generateCard")
    public ResponseEntity generateCard(){
        LibraryCard newCard = cardService.generateCard();
        String result = "The new card is Generated with cardId :" + newCard.getCardId();
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PutMapping("/associateWithStudent")
    public ResponseEntity associateWithStudent(@RequestParam("studentId") Integer Id,
                                               @RequestParam("cardId") Integer cardId){
        String result = cardService.associateStudentWithCard(Id,cardId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
