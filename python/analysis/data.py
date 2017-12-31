import mysql.connector

class DataLoader(object):

    def __init__(self, user='root', password='iznauy.top', database='njult'):
        self.conn = mysql.connector.connect(user=user, password=password, database=database)

    def __del__(self):
        self.conn.close()

    def cursor(self):
        return self.conn.cursor()

    def commit(self):
        self.conn.commit()


_loader = DataLoader()


def load_commodity():
    cursor = _loader.cursor()
    cursor.execute('select * from commodity')
    commodities = cursor.fetchall()
    cursor.close()
    return commodities


def load_sell_record(c_id=None):
    cursor = _loader.cursor()
    if c_id is None:
        cursor.execute("select commodity_id, count, amount, change_time from repository_change where type='SELL'")
    else:
        cursor.execute("select commodity_id, count, amount, change_time from repository_change where type='SELL' "
                       "and commodity_id='%s'" % c_id)
    sell_record = cursor.fetchall()
    cursor.close()
    return sell_record