package com.library.utilities;


import com.library.pojos.Book;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.*;

public class APIUtility {
    private static String URI = ConfigurationReader.getProperty("book.uri");

    /**
     * This method can POST
     *
     * @return response object
     */
    public static Response postBook(Book book) {
        Response response = given().
                contentType(ContentType.JSON).
                basePath(URI).
                body(book).
                when().
                post("/books");
        return response;
    }

    /**
     * This method can POST
     *
     * @param
     * @return response object
     */
    public static Response postBook(Map<String, ?> book) {
        RestAssured.baseURI = ConfigurationReader.getProperty("spartan.uri");
        Response response = given().
                contentType(ContentType.JSON).
                body(book).
                when().
                post("/books");
        return response;
    }


    /**
     * This method can POST
     *
     * @param filePath to the Book external JSON file
     * @return response object
     */
    public static Response postBook(String filePath) {
        RestAssured.baseURI = ConfigurationReader.getProperty("book.uri");
        File file = new File(filePath);
        RestAssured.baseURI = ConfigurationReader.getProperty("book.uri");
        Response response = given().
                contentType(ContentType.JSON).
                body(file).
                when().
                post("/books");
        return response;
    }

    /**
     * Method to delete
     *
     * @param id of book that you would like to delete
     * @return response object that you can assert later
     */
    public static Response deleteBookById(int id) {
        RestAssured.baseURI = ConfigurationReader.getProperty("book.uri");
        return RestAssured.when().delete("/books/{id}", id);
    }

    /**
     * Delete all books
     *
     * @return response
     */
    public static void deleteAllSpartans() {
        Response response = given().
                basePath(baseURI).
                accept(ContentType.JSON).
                when().
                get("/books");

        List<Integer> userIDs = response.jsonPath().getList("id");
        for (int i = 0; i < userIDs.size(); i++) {

            when().delete("/books/{id}", userIDs.get(i)).then().assertThat().statusCode(204);
            System.out.println("Deleted book with id: " + userIDs.get(i));
        }
    }



    public static boolean hasDuplicates(List list) {
        boolean hasDuplicates = false;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (list.get(i).equals(list.get(j)) && i != j) {
                    hasDuplicates = true;
                    System.out.println("Duplicate: " + list.get(i));
                    break;
                }
            }
        }
        return hasDuplicates;
    }
}