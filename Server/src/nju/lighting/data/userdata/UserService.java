package nju.lighting.data.userdata;

import nju.lighting.po.user.UserPO;
import shared.Identity;

import java.util.List;

/**
 * Created on 2017/12/8.
 * Description:
 *
 * @author iznauy
 */
public interface UserService {

    List<UserPO> getByIdentity(Identity identity);

}
