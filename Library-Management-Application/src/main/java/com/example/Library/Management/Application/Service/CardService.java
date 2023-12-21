package com.example.Library.Management.Application.Service;

import com.example.Library.Management.Application.Entities.LibraryCard;
import com.example.Library.Management.Application.Entities.Student;
import com.example.Library.Management.Application.Enums.CardStatus;
import com.example.Library.Management.Application.Repository.CardRepository;
import com.example.Library.Management.Application.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.String;
import java.util.Optional;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private StudentRepository studentRepository;

    public LibraryCard generateCard(){
        LibraryCard card = new LibraryCard();
        card.setCardStatus(CardStatus.New);
        card = cardRepository.save(card);
        return card;
    }

    public String associateStudentWithCard(Integer studentId, Integer cardId){

        //Get object/entity by provided PK
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        Student student = optionalStudent.get();

        Optional<LibraryCard> optionalLibraryCard = cardRepository.findById(cardId);
        LibraryCard libraryCard = optionalLibraryCard.get();

        // saving/updating the attributes for library entity
        libraryCard.setCardStatus(CardStatus.Active);
        libraryCard.setNameOnCard(student.getName());

        libraryCard.setStudent(student);
        //saving/updating the attributes for student entity
        student.setLibraryCard(libraryCard);

        studentRepository.save(student);

        return "Card with "+cardId+" has been associated to student with "+studentId;

    }
}
