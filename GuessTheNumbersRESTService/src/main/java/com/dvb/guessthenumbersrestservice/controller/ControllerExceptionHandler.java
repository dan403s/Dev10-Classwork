/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.guessthenumbersrestservice.controller;

import com.dvb.guessthenumbersrestservice.service.GameAlreadyCompletedException;
import com.dvb.guessthenumbersrestservice.service.InputValidationException;
import com.dvb.guessthenumbersrestservice.service.InvalidIdException;
import com.dvb.guessthenumbersrestservice.service.NoGamesExistException;
import com.dvb.guessthenumbersrestservice.service.NoRoundsForGameIdException;
import java.sql.SQLIntegrityConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author Daniel Bart
 */
@ControllerAdvice
@RestController
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    // to handle SQLIntegrityContraintViolationException
    private static final String CONSTRAINT_MESSAGE = "Could not save your item. Please ensure it is valid and try again.";

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public final ResponseEntity<ControllerError> handleSqlException(
            SQLIntegrityConstraintViolationException ex,
            WebRequest request) {

        ControllerError err = new ControllerError();
        err.setMessage(CONSTRAINT_MESSAGE);
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // to handle GameAlreadyCompletedException
    private static final String GAME_ALREADY_COMPLETED_EXCEPTION_MESSAGE = "This game is already complete. You cannot guess anymore.";

    @ExceptionHandler(GameAlreadyCompletedException.class)
    public final ResponseEntity<ControllerError> handleGameAlreadyCompletedException(
            GameAlreadyCompletedException ex,
            WebRequest request) {

        ControllerError err = new ControllerError();
        err.setMessage(GAME_ALREADY_COMPLETED_EXCEPTION_MESSAGE);
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // to handle InputValidationException
    private static final String INPUT_VALIDATION_EXCEPTION_MESSAGE = "Your guess is not in the correct format. Please enter a four-digit number.";

    @ExceptionHandler(InputValidationException.class)
    public final ResponseEntity<ControllerError> handleInputValidationException(
            InputValidationException ex,
            WebRequest request) {

        ControllerError err = new ControllerError();
        err.setMessage(INPUT_VALIDATION_EXCEPTION_MESSAGE);
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // to handle EmptyResultDataAccessException
    private static final String EMPTY_RESULT_DATA_ACCESS_EXCEPTION_MESSAGE = "Sorry. That Game Id does NOT exist...";

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public final ResponseEntity<ControllerError> handleEmptyResultDataAccessException(
            EmptyResultDataAccessException ex,
            WebRequest request) {

        ControllerError err = new ControllerError();
        err.setMessage(EMPTY_RESULT_DATA_ACCESS_EXCEPTION_MESSAGE);
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // to handle NoRoundsForGameIdException
    private static final String NO_ROUNDS_FOR_GAME_ID_EXCEPTION_MESSAGE = "Sorry. There are no rounds for that Game Id.";

    @ExceptionHandler(NoRoundsForGameIdException.class)
    public final ResponseEntity<ControllerError> handleNoRoundsForGameIdException(
            NoRoundsForGameIdException ex,
            WebRequest request) {

        ControllerError err = new ControllerError();
        err.setMessage(NO_ROUNDS_FOR_GAME_ID_EXCEPTION_MESSAGE);
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // to handle NoGamesExistException
    private static final String NO_GAMES_EXIST_EXCEPTION = "There are no games. Please begin game.";

    @ExceptionHandler(NoGamesExistException.class)
    public final ResponseEntity<ControllerError> handleNoGamesExistException(
            NoGamesExistException ex,
            WebRequest request) {

        ControllerError err = new ControllerError();
        err.setMessage(NO_GAMES_EXIST_EXCEPTION);
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // to handle DataIntegrityViolationException
    private static final String DATA_INTEGRITY_VIOLATION_EXCEPTION = "Invalid Id.";

    @ExceptionHandler(DataIntegrityViolationException.class)
    public final ResponseEntity<ControllerError> handleDataIntegrityViolationException(
            DataIntegrityViolationException ex,
            WebRequest request) {

        ControllerError err = new ControllerError();
        err.setMessage(DATA_INTEGRITY_VIOLATION_EXCEPTION);
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // to handle InvalidIdException
    private static final String INVALID_ID_EXCEPTION_MESSAGE = "Invalid Id.";

    @ExceptionHandler(InvalidIdException.class)
    public final ResponseEntity<ControllerError> handleInvalidIdException(
            InvalidIdException ex,
            WebRequest request) {

        ControllerError err = new ControllerError();
        err.setMessage(INVALID_ID_EXCEPTION_MESSAGE);
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
