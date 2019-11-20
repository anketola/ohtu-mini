/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuminiprojekti.dao;

import ohtuminiprojekti.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *
 * @author hotaru
 */
public interface BookRepository extends JpaRepository<Book, Long> {
 
}
