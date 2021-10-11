import { Authority } from './authority.entity';
import { Entity, Column, ManyToMany, JoinTable } from 'typeorm';
import { BaseEntity } from './base/base.entity';
import { Exclude } from 'class-transformer';

@Entity('users')
export class User extends BaseEntity {
    @Column({ unique: true, nullable: true })
    login: string;
    @Column({ nullable: true })
    fullName?: string;
    @Column({ nullable: true })
    email: string;
    @Column({ unique: true })
    phone: string;
    @Column({ default: false })
    activated?: boolean;

    @ManyToMany(() => Authority)
    @JoinTable()
    authorities?: any[];

    @Column({
        type: 'varchar',
    })
    @Exclude()
    password: string;
    @Column({ nullable: true })
    imageUrl?: string;

}
