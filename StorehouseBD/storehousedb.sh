#!/bin/bash

#database="storehousebd"

#su postgres
#pg_restore -d storehousebd /db/db.dump
#cat storehousebd.sql
psql -U postgres -f storehousebd.sql

#psql -U postgres
#createdb storehousebd
#CREATE DATABASE storehousebd;
#\c storehousebd

