package edu.poly.duan1.services;

import edu.poly.duan1.model.NCC;
import java.util.List;

/**
 *
 * @author AnhTiTan
 */
public interface NCCService {

    public List<NCC> getAll();

    public Boolean saveOrUpdate(NCC ncc);

    public Boolean delete(NCC ncc);

    public NCC getObjbyMa(String ma);
}
