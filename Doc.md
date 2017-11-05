| 名称            | 条目   | 详情                                       |
| ------------- | ---- | ---------------------------------------- |
|               | 语法   | `public DocVO createDoc(DocType type) throws RemoteException` |
| Doc.createDoc | 前置条件 | 传入非空的DocType                             |
|               | 后置条件 | 返回一个空的DocVO                              |
|               | 语法   | `public ResultMessage commitDoc(DocVO doc) throws RemoteException` |
| Doc.commitDoc | 前置条件 | 传入对象不为null，且包含信息正确有效                     |
|               | 后置条件 | 提交Doc                                    |
|               | 语法   | `public ArrayList<DocVO> getDoc(DocFilter filter) throws RemotionException` |
| Doc.getDoc    | 前置条件 | 传入一个非空DocFilter                          |
|               | 后置条件 | 返回符合DocFilter的所有Doc                      |
|               |      |                                          |
|               |      |                                          |

