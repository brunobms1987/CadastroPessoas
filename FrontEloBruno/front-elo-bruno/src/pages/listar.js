import React, { useState, useEffect } from 'react'
import { BrowserRouter as Router, Route, Link, Routes, useParams, useNavigate } from 'react-router-dom'
import axios from 'axios'
import Editar from './editar'
import Excluir from './excluir'

function Listar() {

    const [listagem, setLista] = useState([])

    useEffect(() => {
        axios.get("http://localhost:4200/listar")
            .then((response) => {
                setLista(response.data)
            }).catch(() => {
                console.log("Erro ao retornar lista de pessoas.")
            })
    }, [])

    function excluirPessoa(id) {
        axios.delete(`http://localhost:4200/excluir/${id}`)
        setLista(listagem.filter(lista => lista._id !== id))
    }

    return (
        <div>
            <h1>Lista de Pessoas</h1>
            <Link to="/">
                <button>Voltar pro Início</button>
            </Link>
            <Routes>
                <Route path="/editar/:id" Component={Editar} />
            </Routes>
            {listagem.map((lista, key) => {
                return (
                    <div className='tabela-lista' key={key}>
                        <table border="1">
                            <caption><h1>Lista de Pessoas</h1></caption>
                            <tr>
                                <td>Nome</td>
                                <td>CPF</td>
                                <td>Data de Nascimento</td>
                                <td>Contatos</td>
                            </tr>
                            <tr>
                                <td>{lista.nome}</td>
                                <td>{lista.CPF}</td>
                                <td>{lista.dataNascimento}</td>
                                <td>
                                    <tr>
                                        <td>Nome</td>
                                        <td>E-mail</td>
                                        <td>Telefone</td>
                                    </tr>
                                    <tr>
                                        <td>{lista.nome}</td>
                                        <td>{lista.email}</td>
                                        <td>{lista.telefone}</td>
                                    </tr>
                                </td>
                            </tr>
                            <tr>
                                <td><Link to={{ pathname: `/editar/${lista._id}` }}>Editar</Link></td>
                            </tr><tr>
                                <td><button onCLick={() => excluirPessoa(lista._id)} >Excluir</button></td>
                            </tr>
                        </table>
                    </div>
                )
            })}
        </div>
    )
}

export default Listar