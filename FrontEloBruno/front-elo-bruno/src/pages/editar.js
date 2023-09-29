import React, { useEffect } from 'react'
import { Link, useParams, useNavigate } from 'react-router-dom'
import { useForm } from 'react-hook-form'
import { yupResolver } from "@hookform/resolvers/yup"
import * as yup from "yup"
import { cpf } from "cpf-cnpj-validator";
import axios from 'axios'

const validationEdit = yup.object().shape({
    nome: yup.string().required("O campo NOME é obrigatório."),
    cpf: yup.string().required("O campo CPF é obrigatório.").max(11).test((value) => cpf.isValid(value)),
    dataNascimento: yup.date().required("O campo DATA DE NASCIMENTO é obrigatório."),
    contato: yup.string().required("O campo CONTATO é obrigatório.")
})

function Editar() {

    const { id } = useParams()
    let history = useNavigate()

    const { register, handleSubmit, formState: { errors }, reset } = useForm({
        resolver: yupResolver(validationEdit)
    })

    const incluir = data => axios.put(`http://localhost:4200/editar/${id}`, data)
        .then(() => {
            console.log("Pessoa incluída com sucesso.")
            history.push('/')
        }).catch(() => {
            console.log("Erro ao incluir pessoa.")
        })

        useEffect(() => {
            axios.get(`http://localhost:4200/${id}`)
            .then((response) => {
                reset(response.data)
            })
        }, [])
    
        return (
        <div>
            <div>
                <h1>Page Editar</h1>
                <Link to="/">
                    <button>Voltar pro Início</button>
                </Link>


                <main>
                    <div className='formulario-inteiro'></div>
                    <h1>Adicionar Pessoa</h1>
                    <div className='campos-form'>
                        <label>Nome: </label>
                        <input type="text" name="nome" {...register("nome")} 
                        placeholder='Nome da pessoa'/>
                        <p className='error-message'>{errors.nome?.message}</p>
                    </div>
                    <div className='campos-form'>
                        <label>CPF: </label>
                        <input type="text" name="cpf" {...register("cpf")} 
                        pattern="\d{3}\.\d{3}\.\d{3}-\d{2}"
                        placeholder='000.000.000-00'/>
                        <p className='error-message'>{errors.cpf?.message}</p>
                    </div>
                    <div className='campos-form'>
                        <label>Data de Nascimento: </label>
                        <input type="date" name="dataNascimento" {...register("dataNascimento")} />
                        <p className='error-message'>{errors.dataNascimento?.message}</p>
                    </div>
                    <div className='campos-form'>
                        <label>Contato: </label>
                        <input type="text" name="contato"></input>
                        <p className='error-message'>{errors.contato?.message}</p>
                    </div>
                    <div className='btn-post'>
                        <label>Editar </label>
                        <input type="submit"></input>
                    </div>
                </main>
            </div>
        </div>
    )
}

export default Editar