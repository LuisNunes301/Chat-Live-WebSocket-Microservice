"use client";

import { useState } from "react";
import { useMutation } from "@tanstack/react-query";
import { loginUser } from "@/api/apiUser";
import { LoginRequest, LoginResponse } from "@/types/user";
import { useRouter } from "next/navigation";

export default function LoginForm() {
  const router = useRouter();
  const [form, setForm] = useState<LoginRequest>({
    email: "",
    password: "",
  });

  const mutation = useMutation<LoginResponse, Error, LoginRequest>({
    mutationFn: loginUser,
    onSuccess: (data) => {
      // Salva o token e email em cookies
      document.cookie = `token=${data.message}; path=/;`;
      document.cookie = `email=${data.email}; path=/;`;

      alert("Login realizado com sucesso!");
      router.push("/chat");
    },
    onError: (err) => {
      alert(err.message || "Erro ao fazer login");
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
      <h2 className="text-xl font-bold">Login</h2>
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
        className="bg-green-600 text-white p-2 rounded"
      >
        {mutation.isPending ? "Entrando..." : "Entrar"}
      </button>
    </form>
  );
}
