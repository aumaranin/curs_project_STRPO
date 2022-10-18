FROM postgres:latest

ADD ${PWD}/pg_hba.conf /var/lib/postgresql/data/

ADD ${PWD}/postgresql.conf /var/lib/postgresql/data/

COPY ${PWD}/init.sql /docker-entrypoint-initdb.d/

CMD ["service" "postgresql" "start"]

