package nju.lighting.bl.repositorybl;

import shared.ResultMessage;

/**
 * Created on 2017/11/26.
 * Description:
 *
 * @author iznauy
 */
public interface RepositoryInfo {

    ResultMessage changeRepository(RepositoryChange change);

}
