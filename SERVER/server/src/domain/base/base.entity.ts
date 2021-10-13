import { PrimaryGeneratedColumn, Column, CreateDateColumn, UpdateDateColumn } from 'typeorm';

export abstract class BaseEntity {
    @PrimaryGeneratedColumn('uuid')
    id?: string;

    @Column({ nullable: true })
    createdBy?: string;
    @Column({ nullable: true, type: 'timestamp', default: () => 'CURRENT_TIMESTAMP'})
    createdDate?: Date;
    @Column({ nullable: true })
    lastModifiedBy?: string;
    @Column({ nullable: true, type: 'timestamp', default: () => 'CURRENT_TIMESTAMP'})
    lastModifiedDate?: Date;
}
