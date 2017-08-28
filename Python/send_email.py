import smtplib
import time
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
from email.utils import COMMASPACE, formatdate

MAIL_LOGIN = "login@address.com"
MAIL_PASSWORD = "password"
SMTP_HOST = "smtp.host.host2.com"
SMTP_PORT = 25
EMAILS_TO_NOTIFY = [
    "jan.kowalski@whatever.com",
    "john.kowalski@whatever.com", 
    ]

SUBJECT = "System status check %s" % DAY_STR

body = ""

def create_mail_body(report):
    """
    Method takes string report and return HTML-like version
    :param report: Final report in
    :return: String formatted as html document
    """
    html_body = """
    <html>
      <head></head>
      <body>
        <p>
           <font face="Consolas, DINPro-Light, Arial">{report}</font>
        </p>
      </body>
    </html>
    """

    report_to_send = report\
        .replace("\r\n", "<br>")\
        .replace("\n", "<br>")\
        .replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;") \
        .replace("<br><br><br>", "<br><br>")

    return html_body.format(report=report_to_send)


msg = MIMEMultipart('alternative')
msg['From'] = MAIL_LOGIN
msg['To'] = COMMASPACE.join(EMAILS_TO_NOTIFY)
msg['Date'] = formatdate(localtime=True)
msg['Subject'] = SUBJECT
text_part = MIMEText(body, _subtype='plain', _charset='utf-8')
html_part = MIMEText(create_mail_body(body), _subtype='html', _charset='utf-8')
msg.attach(text_part)
msg.attach(html_part)

# Send previously prepared email
try_connect_counter = 1
while try_connect_counter < 4:
    try:
        smtp_client = smtplib.SMTP(SMTP_HOST, SMTP_PORT)
        smtp_client.starttls()
        smtp_client.login(MAIL_LOGIN, MAIL_PASSWORD)
        smtp_client.sendmail(MAIL_LOGIN, EMAILS_TO_NOTIFY, msg.as_string())
        smtp_client.quit()
        break
    except:
        print("Error while trying to connect")
        try_connect_counter += 1
        time.sleep(20)
