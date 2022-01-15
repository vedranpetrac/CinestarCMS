/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.cinestar.dal;
import hr.cinestar.dal.sql.SqlRepository;


/**
 *
 * @author Vedran
 */
public class RepositoryFactory {

    private RepositoryFactory() {
    }

    public static Repository getRepository() throws Exception {
        return new SqlRepository();

    }

}
