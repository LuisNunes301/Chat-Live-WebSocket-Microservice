"use client";

import { useState } from "react";
import { useMutation } from "@tanstack/react-query";
import { createUser } from "@/api/apiUser";
import { User } from "@/types/user";
import { useRouter } from "next/navigation";

export default function SignupForm() {
  const router = useRouter();
  const [form, setForm] = useState<User>({
    name: "",
    email: "",
    password: "",
  });

  const mutation = useMutation({
    mutationFn: createUser,
    onSuccess: () => {
      alert("Usuário criado com sucesso!");
      router.push("/login"); // redireciona para login
    },
    onError: (err: any) => {
      alert(err.message || "Erro ao criar usuário");
    },
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    mutation.mutate(form);
  };

  return (
    <form
      onSubmit={handleSubmit}
      className="flex flex-col gap-3 p-4 border rounded-md max-w-sm mx-auto mt-75"
    >
      <h2 className="text-xl font-bold">Cadastro</h2>
      <input
        type="text"
        name="name"
        placeholder="Nome"
        value={form.name}
        onChange={handleChange}
        className="border p-2 rounded"
        required
      />
      <input
        type="email"
        name="email"
        placeholder="Email"
        value={form.email}
        onChange={handleChange}
        className="border p-2 rounded"
        required
      />
      <input
        type="password"
        name="password"
        placeholder="Senha"
        value={form.password}
        onChange={handleChange}
        className="border p-2 rounded"
        required
      />
      <button
        type="submit"
        disabled={mutation.isPending}
        className="bg-blue-600 text-white p-2 rounded"
      >
        {mutation.isPending ? "Criando..." : "Cadastrar"}
      </button>
    </form>
  );
}