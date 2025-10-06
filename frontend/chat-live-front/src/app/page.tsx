"use client";

import Link from "next/link";

export default function HomePage() {
  return (
    <main className="flex flex-col items-center justify-center min-h-screen p-8">
      <h1 className="text-3xl font-bold">Bem-vindo ao Sistema de Reservas</h1>
      <div className="mt-6 flex gap-4">
        <Link href="/login" className="px-4 py-2 bg-blue-500 text-white rounded">
          Login
        </Link>
        <Link href="/signup" className="px-4 py-2 bg-green-500 text-white rounded">
          Cadastro
        </Link>
      </div>
    </main>
  );
}
