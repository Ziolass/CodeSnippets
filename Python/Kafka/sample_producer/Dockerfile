FROM python:3.6

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

COPY . /usr/src/app
RUN pip install --no-cache-dir -r requirements.txt

CMD [ "python", "-u", "/usr/src/app/sample_producer.py" ]
