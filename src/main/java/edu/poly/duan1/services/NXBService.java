package edu.poly.duan1.services;

import edu.poly.duan1.model.NXB;
import java.util.List;

/**
 *
 * @author AnhTiTan
 */
public interface NXBService {

    public List<NXB> getAll();

    public Boolean saveOrUpdate(NXB nxb);

    public Boolean delete(NXB nxb);

    public NXB getObjbyMa(String ma);
}
