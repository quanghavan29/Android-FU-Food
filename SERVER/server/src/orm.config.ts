import { TypeOrmModuleOptions } from '@nestjs/typeorm';

function ormConfig(): TypeOrmModuleOptions {
    const commonConf = {
        SYNCRONIZE: false,
        ENTITIES: [__dirname + '/domain/*.entity{.ts,.js}'],
        MIGRATIONS: [__dirname + '/migrations/**/*{.ts,.js}'],
        CLI: {
            migrationsDir: 'src/migrations',
        },
        MIGRATIONS_RUN: true,
    };

    let ormconfig: TypeOrmModuleOptions = {
        name: 'default',
        type: 'mysql',
        database: 'fpt_food',
        host: '183.91.11.126',
        port: 3306,
        username: 'root',
        password: 'root@gfcd',
        logging: false,
        synchronize: true,
        entities: commonConf.ENTITIES,
        migrations: commonConf.MIGRATIONS,
        cli: commonConf.CLI,
        migrationsRun: commonConf.MIGRATIONS_RUN,
    };

    if (process.env.BACKEND_ENV === 'prod') {
        ormconfig = {
            name: 'default',
            type: 'mysql',
            database: 'fpt_food',
            host: '183.91.11.126',
            port: 3306,
            username: 'root',
            password: 'root@gfcd',
            logging: false,
            synchronize: commonConf.SYNCRONIZE,
            entities: commonConf.ENTITIES,
            migrations: commonConf.MIGRATIONS,
            cli: commonConf.CLI,
            migrationsRun: commonConf.MIGRATIONS_RUN,
        };
    }

    if (process.env.BACKEND_ENV === 'test') {
        ormconfig = {
            name: 'default',
            type: 'mysql',
            database: 'fpt_food',
            host: '183.91.11.126',
            port: 3306,
            username: 'root',
            password: 'root@gfcd',
            keepConnectionAlive: true,
            logging: false,
            synchronize: true,
            entities: commonConf.ENTITIES,
            migrations: commonConf.MIGRATIONS,
            cli: commonConf.CLI,
            migrationsRun: commonConf.MIGRATIONS_RUN,
        };
    }
    return ormconfig;
}

export { ormConfig };
