| 名称                            | 条目   | 详情                                       |
| ----------------------------- | ---- | ---------------------------------------- |
|                               | 语法   | `public DocVO createDoc(DocType type) throws RemoteException` |
| Doc.createDoc                 | 前置条件 | 传入非空的DocType                             |
|                               | 后置条件 | 返回一个空的DocVO                              |
|                               | 语法   | `public ResultMessage commitDoc(DocVO doc) throws RemoteException` |
| Doc.commitDoc                 | 前置条件 | 传入对象不为null，且包含信息正确有效                     |
|                               | 后置条件 | 提交Doc                                    |
|                               | 语法   | `public ArrayList<DocVO> getDoc(DocFilter filter) throws RemotionException` |
| Doc.getDoc                    | 前置条件 | 传入一个非空DocFilter                          |
|                               | 后置条件 | 返回符合DocFilter的所有Doc                      |
|                               | 语法   | `public ArrayList<SaleRecordItemVO> findSaleRecords(saleRecordFilterVO vo)` |
| Doc.findSaleRecords           | 前置条件 | 用户已输入正确的筛选条件并确认                          |
|                               | 后置条件 | 返回符合筛选条件的所有销售记录的值对象                      |
|                               | 语法   | `public ArrayList<BussinessHitoryItemVO> findDocuments(documentFilterVO vo)` |
| Doc.findDocuments             | 前置条件 | 用户已输入正确的筛选条件并确认                          |
|                               | 后置条件 | 返回符合筛选条件的所有单据的值对象                        |
|                               | 语法   | `public RevenueAndExpenditureVO findRevenueAndExpenditure(long StartDate,long endDate)` |
| Doc.findRevenueAndExpenditure | 前置条件 | 用户已输入正确的筛选条件并确认                          |
|                               | 后置条件 | 返回所输入时间段内的收支情况的值对象                       |
|                               | 语法   | `public ResultMessage redflush(DocVO target) throws RemoteException` |
| Doc.redflush                  | 前置条件 | 传入有效的DocVO对象                             |
|                               | 后置条件 | 将对应红冲单据入账，更新持久化存储，更新显示                   |
|                               | 语法   | `public DocVO redflushAndCopy(DocVO target) throws RemoteException` |
| Doc.redflushAndCopy           | 前置条件 | 传入有效的DocVO对象                             |
|                               | 后置条件 | 返回各属性取负后的相应的DocVO对象                      |