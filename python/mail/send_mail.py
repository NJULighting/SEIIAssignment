# coding=utf-8
import smtplib
from email.mime.text import MIMEText
from email.header import Header
import sys

author_mail = "njulighting@126.com"
password = "njult123"
smtpserver = "smtp.126.com"

receiver = sys.argv[1] # 第二个命令行参数，表示接受人
content = sys.argv[3] # 第四个命令行参数，表示内容
subject = sys.argv[2] # 第三个命令行参数，表示邮件主题

if __name__ == '__main__':
    msg = MIMEText(content, 'plain', 'gbk')
    msg['Subject'] = Header(subject, 'gbk')
    msg['From'] = author_mail
    msg['To'] = receiver
    smtp = smtplib.SMTP()
    smtp.connect(smtpserver)
    smtp.login(author_mail, password)
    smtp.sendmail(author_mail, receiver, msg.as_string())
    smtp.quit()
    print '邮件发送了，ojbk！'
